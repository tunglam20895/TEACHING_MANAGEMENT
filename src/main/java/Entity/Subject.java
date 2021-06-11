package Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "SUBJECT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "TOTAL_LESSON")
	private long totalLesson;

	@Column(name = "THEORY_LESSON")
	private long theoryLesson;

	@Column(name = "THEORY_EXPENSE")
	private long theoryExpense;

	@OneToMany(mappedBy = "subject",fetch = FetchType.LAZY)
	private Collection<TeacherTimeSheet> teacherTimeSheets;

	public Subject(String name, long totalLesson, long theoryLesson, long theoryExpense) {
		this.name = name;
		this.totalLesson = totalLesson;
		this.theoryLesson = theoryLesson;
		this.theoryExpense = theoryExpense;
	}

}
