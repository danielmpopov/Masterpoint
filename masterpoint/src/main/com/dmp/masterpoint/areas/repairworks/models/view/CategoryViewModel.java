package com.dmp.masterpoint.areas.repairworks.models.view;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

public class CategoryViewModel {

    private String id;

    private String name;

    private Set<SubCategoryViewModel> subCategories;

    public CategoryViewModel() {
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

    public Set<SubCategoryViewModel> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<SubCategoryViewModel> subCategories) {
        this.subCategories = subCategories;
    }
}
