package com.shawon.UserService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "users")
@CompoundIndex(name = "email_name_idx", def = "{'email': 1, 'name': 1}", unique = true)
public class User {
    @Id
    private String id;
    private String name;
    private String password;

    @Indexed
    private String email;
}
