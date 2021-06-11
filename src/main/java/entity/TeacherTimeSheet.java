package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TEACHING_TIME_SHEET")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherTimeSheet {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;

	@Column(name = "CLASS_NUMBER")
	private long classNumber;

	@ManyToOne
	@JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID")
	private Teacher teacher;

	@ManyToOne
	@JoinColumn(name = "SUBJECT_ID", referencedColumnName = "ID")
	private Subject subject;

	public TeacherTimeSheet(long classNumber, Teacher teacher, Subject subject) {
		this.classNumber = classNumber;
		this.teacher = teacher;
		this.subject = subject;
	}
}
