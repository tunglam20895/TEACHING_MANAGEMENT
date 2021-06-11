package Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "TEACHER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "TEACHER_LEVEL")
	private String teacherLevel;

	@OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY)
	private Collection<TeacherTimeSheet> teacherTimeSheets;


	public Teacher(String name, String address, String phoneNumber, String teacherLevel) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.teacherLevel = teacherLevel;
	}

}
