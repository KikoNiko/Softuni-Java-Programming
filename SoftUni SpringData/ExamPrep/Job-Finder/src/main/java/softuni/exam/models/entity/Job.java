package softuni.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "jobs")
public class Job extends BaseEntity {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(name = "hoursaweek", nullable = false)
    private double hoursAWeek;
    @Column(nullable = false)
    private double salary;
    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getHoursAWeek() {
        return hoursAWeek;
    }

    public void setHoursAWeek(double hoursAWeek) {
        this.hoursAWeek = hoursAWeek;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
