package io.wakelesstuna.jpaprojectiondemo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "files")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {
    @Id
    private String id;
    private String owner;
    private String name;
    private String type;
    private Long size;
    @Lob
    private byte[] content;
}
