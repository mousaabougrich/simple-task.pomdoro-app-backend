package stage_test.taskmmanagerapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String rolename;
;
}
