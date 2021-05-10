package com.zemoso.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name="student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	// define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@NotBlank(message = "Name is mandatory")
	@Pattern(regexp ="[a-zA-Z]+",message="invalid Entry")
	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	@NotBlank(message = "Last Name is mandatory")
	@Pattern(regexp ="[a-zA-Z]+",message="invalid Entry")
	private String lastName;

	@Column(name="email")
	@NotBlank(message = "Email is mandatory")
	private String email;

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
	@JoinTable(name = "student_has_course",
			joinColumns = @JoinColumn(name = "student_id"),
			inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses;

}











