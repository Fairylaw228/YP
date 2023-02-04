package com.hludencov.java_spring.models;

import com.hludencov.java_spring.interfaces.IExelExport;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Entity
public class Document implements IExelExport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id")
    public User user;

    public String fileName;

    @PastOrPresent
    public Date date;

    @FutureOrPresent
    public Date archive_date;

    @Value("false")
    public boolean toAdmission;
//    @PositiveOrZero
    public double averageMark;

    public Document_status status;

    @OneToMany(mappedBy = "document", fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private Set<Summary> summaries;


    //______________________________BOILERPLATE LINE__________________________________


    public boolean isToAdmission() {
        return toAdmission;
    }

    public void setToAdmission(boolean toAdmission) {
        this.toAdmission = toAdmission;
    }

    public Set<Summary> getSummaries() {
        return summaries;
    }

    public void setSummaries(Set<Summary> summaries) {
        this.summaries = summaries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getArchive_date() {
        return archive_date;
    }

    public void setArchive_date(Date archive_date) {
        this.archive_date = archive_date;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }

    public Document_status getStatus() {
        return status;
    }

    public void setStatus(Document_status status) {
        this.status = status;
    }

    public List<Object> getHeaders() {
        return Arrays.asList(
                "Имя файла",
                "Пользователь",
                "Средний балл",
                "Дата загрузки",
                "Дата архивации"
        );
    }
    public List<Object> getData() {
        return Arrays.asList(
                getFileName(),
                getUser().getPersonal_info().name +" "+ getUser().getPersonal_info().getSec_name(),
                getAverageMark(),
                getDate().toString(),
                getArchive_date().toString()
        );
    }
}
