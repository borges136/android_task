import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Represents a social profile
 */
public class Profile {
    // static id generator shared among all instances of Profile
    // atomic for thread safety
    private static final AtomicLong idGenerator = new AtomicLong(0L);

    private String firstName;
    private String surName;
    private Double age;

    private final Long id;
    private UUID uuid;

    public Profile(String firstName, String surName, Double age) {
        this.firstName = firstName;
        this.surName = surName;
        this.age = age;
        id = idGenerator.getAndIncrement(); //increment the atomic autoincrement ID

        // create a UUID based on first name and last name, as per requirements
        uuid = UUID.nameUUIDFromBytes(String.valueOf(new StringBuilder(firstName).append(" ").append(surName)).getBytes());
    }

    /* getters and setters */

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public Double getAge() {
        return age;
    }

    public UUID getUuid() {
        return uuid;
    }
    public Long getId() {
        return  id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return getFirstName().equals(profile.getFirstName()) && getSurName().equals(profile.getSurName()) && getAge().equals(profile.getAge()) && getId().equals(profile.getId()) && getUuid().equals(profile.getUuid());
    }


    @Override
    public String toString() {
        return "Profile{" + "firstName='" + firstName + '\'' + ", surName='" + surName + '\'' + ", age=" + age + ", id=" + id + ", uuid=" + uuid + '}';
    }
}
