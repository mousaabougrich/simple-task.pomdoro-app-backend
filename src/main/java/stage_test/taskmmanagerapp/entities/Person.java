package stage_test.taskmmanagerapp.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;


    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Todo> todoList;
 @ManyToMany(fetch = FetchType.EAGER)
    public Collection<AppRole> roles = new ArrayList<>();
}
