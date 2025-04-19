import React from 'react'
import './StudentList.css'

const StudentList = ({ students }) => {
    return (
        <ul className='student-list'>
            {
                students.map((student, index) =>
                    <li key={index}>
                        <h3>{student.name}</h3>
                        <p>Age: {student.age}</p>
                        <p>Email: {student.email}</p>
                        <p>Phone: {student.phone}</p>
                    {/* change */}
                        {/* <p>Course: {student.courseName}</p>
                        <p>Start Date: {student.courseStartDate}</p> */}
                    </li>
                )
            }
        </ul>
    )
}

export default StudentList