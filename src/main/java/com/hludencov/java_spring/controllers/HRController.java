package com.hludencov.java_spring.controllers;

import com.hludencov.java_spring.models.*;
import com.hludencov.java_spring.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.hludencov.java_spring.interfaces.IExelExport;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/hr")
@PreAuthorize("hasAnyAuthority('HR','ADMISSION','CANDIDATE')")
public class HRController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    SummaryRepository summaryRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    CandidateRepository candidateRepository;

    User user = new User();
    Document doc;


    @PreAuthorize("hasAnyAuthority('HR','ADMISSION')")
    @GetMapping
    public String documentList(Model model) {
        user = getAuthUser();
        model.addAttribute("user", user);
        if (user.getRoles().contains(Role.ADMISSION)) {
            model.addAttribute("documents", documentRepository.findByToAdmission(true));
        } else {
            model.addAttribute("documents", documentRepository.findByToAdmission(false));
        }
        return "hr/hr-main";
    }


    @GetMapping("/editor/{document}")
    public String openEditor(Model model, @PathVariable Document document) {

        user = getAuthUser();

        checkAccess(document);

        var summaries = summaryRepository.findByDocument(document);
        var subjects = subjectRepository.findAll();

        model.addAttribute("summaries", summaries);
        model.addAttribute("document_status", Document_status.values());
        model.addAttribute("document", document);
        model.addAttribute("summary", new Summary());
        model.addAttribute("subjects", getCheckedSubjects(summaries, subjects));
        model.addAttribute("username", document.getUser().getPersonal_info().getName());
        model.addAttribute("preparationProgram", document.getUser().getCandidate_info().preparationProgram.getName());

        doc = document;

        double average = getAverage(getMarks(summaries));

        model.addAttribute("subjects_name", getSubjectsNames(summaries));
        model.addAttribute("marks", getMarks(summaries));
        model.addAttribute("marks_average", String.format("%.2f", average));

        if (average > 1) {
            document.getUser().getCandidate_info().setAverageMark(getAllDocsAverage(document));
            document.averageMark = average;
        }
        documentRepository.save(document);
        return "hr/hr-editor";
    }

    @PostMapping("/editor/add")
    public String editorPostAdd(@ModelAttribute("summary") @Valid Summary summary, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("candidate", candidateRepository.findAll());
            model.addAttribute("subjects", subjectRepository.findAll());
            return "summary/summary-add";
        }
        summary.setDocument(doc);
        summary.setCandidate_info(doc.user.getCandidate_info());

        summaryRepository.save(summary);
        return "redirect:/hr/editor/" + doc.id;
    }

    @GetMapping("/editor/{document}/export")
    public void editorExelExport(HttpServletResponse response, @PathVariable Document document) throws IOException {

        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=SummaryExportOf_" + document.user.getPersonal_info().getName() + "_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        Summary[] data = new Summary[]{};

        data = summaryRepository.findByDocument(document).toArray(data);

        ExelExport exelExport = new ExelExport(data);
        exelExport.generateExcelFile(response);
    }

    //TODO main page exel export
    @GetMapping("/main/export")
    public void mainExelExport(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=AllHrOrCommissionExport_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        Document[] data2 = documentRepository.findByToAdmission(isHrOrAdmission()).toArray(new Document[0]);

        ExelExport exelExport = new ExelExport(data2);
        exelExport.generateExcelFile(response);
    }

    @GetMapping(value = "/editor/update", params = {"mark", "summary"})
    public String editorUpdate(@RequestParam(value = "mark") int mark,
                               @RequestParam(value = "summary") Summary summary) {
        summary.setMark(mark);
        summaryRepository.save(summary);
        return "redirect:/hr/editor/" + doc.id;
    }

    @PreAuthorize("hasAnyAuthority('HR','ADMISSION')")
    @PostMapping(value = "/editor/status/")
    public String editorDecision(@RequestParam(value = "status") Document_status status) {

        doc.setStatus(status);
        documentRepository.save(doc);
        if (status != Document_status.ACCEPTED) return "redirect:/hr/editor/" + doc.id;

        doc.getUser().setRoles(Collections.singleton(Role.STUDENT));
        userRepository.save(doc.getUser());


        return "redirect:/group/candidate/add/" + doc.getUser().getCandidate_info().getId();
    }


    //___________________________________Utils______________________________________________________

    private User getAuthUser() {
        return userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    private double getAllDocsAverage(Document document) {
        List<Double> averageMarks = new ArrayList<Double>();
        for (Document doc : document.user.getDocuments()) {
            if (doc.averageMark >= 2)
                averageMarks.add(doc.averageMark);
        }
        return getAverage(averageMarks);
    }

    private static HashSet<Subject> getCheckedSubjects(List<Summary> summaries, Iterable<Subject> subjects) {
        var subjects_checked = new HashSet<Subject>();

        for (Subject subject : subjects) {
            boolean r = false;
            for (Summary summary : summaries) {
                if (Objects.equals(summary.getSubject().getId(), subject.getId())) {
                    r = true;
                    break;
                }
            }
            if (!r) {
                subjects_checked.add(subject);
            }
        }
        return subjects_checked;
    }

    private static List<Double> getMarks(List<Summary> summaries) {
        List<Double> marks = new ArrayList<Double>();
        for (var sum : summaries) {
            marks.add((double) sum.getMark());
        }
        return marks;
    }

    private static List<String> getSubjectsNames(List<Summary> summaries) {
        List<String> sub_names = new ArrayList<String>();
        for (var sum : summaries) {
            sub_names.add(sum.getSubject().getName());
        }
        return sub_names;
    }

    private static double getAverage(List<Double> marks) {
        double totalSum = 0;
        for (Double mark : marks) {
            totalSum += mark;
        }
        double average = 0;
        if (marks.size() != 0) average = totalSum / marks.size();
        return average;
    }

    private void checkAccess(Document document) {
        if (!(user.getRoles().contains(Role.ADMISSION) || user.getRoles().contains(Role.HR))
                && getAuthUser() != document.user) {
            throw new AccessDeniedException("Access denied");
        }
    }
    private boolean isHrOrAdmission() {
        return getAuthUser().getRoles().contains(Role.HR) || getAuthUser().getRoles().contains(Role.ADMISSION);
    }


}
