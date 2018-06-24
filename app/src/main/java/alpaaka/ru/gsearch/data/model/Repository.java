package alpaaka.ru.gsearch.data.model;

import com.google.gson.annotations.SerializedName;

public class Repository {

    @SerializedName("name")
    private String name;
    @SerializedName("full_name")
    private String title;
    @SerializedName("owner")
    private Owner owner;
    @SerializedName("description")
    private String description;

    public Repository(String name, String fullName, Owner owner, String description) {
        this.name = name;
        this.title = fullName;
        this.owner = owner;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getDescription() {
        return description;
    }
}
