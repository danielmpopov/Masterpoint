package com.dmp.masterpoint.repairworks.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sub_categories")
public class SubCategory {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )

    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;

    @Column(nullable = false)
    private String currencyPerUnit;

    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<RepairWork> repairWorks;

    public SubCategory() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCurrencyPerUnit() {
        return currencyPerUnit;
    }

    public void setCurrencyPerUnit(String currencyPerUnit) {
        this.currencyPerUnit = currencyPerUnit;
    }

    public Set<RepairWork> getRepairWorks() {
        return repairWorks;
    }

    public void setRepairWorks(Set<RepairWork> repairWorks) {
        this.repairWorks = repairWorks;
    }

}
