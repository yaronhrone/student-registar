import { useEffect, useState } from 'react';
import './App.css';
import CourseChart from './components/course-chart/CourseChart';
import CourseFilter from './components/course-filter/CourseFilter';
import RegistrationForm from './components/registration-form/RegistrationForm';
import StudentList from './components/student-list/StudentList';
import RegistrationFilter from './components/registration-filter/RegistrationFilter';
import CourseList from './components/course-list/CourseList';
import axios from 'axios'
import { COURSES_URL, STUDENTS_URL, REGISTRANTS_COUNT_URL } from './constrants/urls';

function App() {
  // const courses = [
  //   { id: "1", name: "HTML", startDate: new Date(2025, 5, 1) },
  //   { id: "2", name: "CSS", startDate: new Date(2025, 6, 1) },
  //   { id: "3", name: "JS", startDate: new Date(2025, 7, 1) },
  //   { id: "4", name: "React", startDate: new Date(2025, 8, 1) },
  //   { id: "5", name: "Angular", startDate: new Date(2025, 9, 1) }
  // ]
  // change
  const [courses, setCourses] = useState([]);
  const [filteredCourses, setFilteredCourses] = useState([])
  const [registrantsCountPerCourse, setRegistrantsCountPerCourse] = useState([]);

  const [students, setStudents] = useState([]);
  const [selectedCourse, setSelectedCourse] = useState("");
  const [selectedRegistrationsNumber, setSelectedRegistrationsNumber] = useState("");

  const fetchCourses = (numberOfStudents = "") => {
    if (numberOfStudents) {
      axios.get(`${COURSES_URL}?numberOfStudent=${numberOfStudents}`)
        .then(response => {
          setFilteredCourses(response.data)
        })
        .catch(error => console.log(error));

    } else {
      axios.get(`${COURSES_URL}`)
        .then(response => {
          setCourses(response.data);
        }).catch(error => console.log(error));
    }
  }
  useEffect(() => {
    fetchCourses(selectedRegistrationsNumber);
  }, [selectedRegistrationsNumber]);
  const fatchStudents = (courseId = "") => {

    axios.get(`${STUDENTS_URL}${courseId ? `?courseId=${courseId}` : ""} `)
    .then(response => {
      setStudents(response.data);
    })
    .catch(error => console.log(error));
  }
  useEffect(() => {
    fatchStudents(selectedCourse);
  }, [selectedCourse]);
const fetchRegistrantsCountPerCourse = () => {
  axios.get(`${REGISTRANTS_COUNT_URL}`)
    .then(response => {
      setRegistrantsCountPerCourse(response.data);
    })
    .catch(error => console.log(error));
} 

  useEffect(() => {
    fetchRegistrantsCountPerCourse();
  }, []);
  
  // change  
  const handleRegister = (newStudent, courseId) => {
  axios.post(`${STUDENTS_URL}?courseId=${courseId}` , newStudent)
  .then(() => {
    if(selectedCourse== courseId || !selectedCourse){
      fatchStudents(selectedCourse);
    }
    if(selectedRegistrationsNumber){
    fetchCourses(selectedRegistrationsNumber);
  }
    fetchRegistrantsCountPerCourse();
  })
  .catch(error => console.log(error));
    // setStudents([...students, { ...newStudent, courseName: courses.find(course => course.id == newStudent.courseId).name }]);
  }
  // const filteredStudents =
  //   selectedCourse ?
  //     students.filter(student => student.courseId == selectedCourse)
  //     :
  //     students;

  // const filteredCourses =
  //   courses.filter(course => {
  //     const studentCount = students.filter(student => student.courseId == course.id).length;
  //     switch (selectedRegistrationsNumber) {
  //       case "0":
  //         return studentCount == 0;
  //       case "1 to 10":
  //         return studentCount >= 1 && studentCount <= 10;
  //       case "11 to 30":
  //         return studentCount >= 11 && studentCount <= 30;
  //       case "over 30":
  //         return studentCount > 30;
  //       default:
  //         return false;
  //     }
  //   });

  return (
    <div className="App">
      <h1>Student Registration System</h1>
      <RegistrationForm courses={courses} onRegister={handleRegister} />
      {/* <CourseChart courses={courses} students={students} /> */}
      {/* {change} */}
      <CourseChart courses={courses} registrantsCountPerCourse={registrantsCountPerCourse} />
      <CourseFilter courses={courses} selectedCourse={selectedCourse} onSelectCourse={setSelectedCourse} />
      <StudentList students={students} />
      <RegistrationFilter selectedRegistrationsNumber={selectedRegistrationsNumber}
        onSelectRegistrationsNumber={setSelectedRegistrationsNumber} />
      <CourseList courses={filteredCourses} />
    </div>
  );
}

export default App;
