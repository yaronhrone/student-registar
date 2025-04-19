import React from 'react'
import './CourseList.css'

const CourseList = ({ courses }) => {
    return (
        <ul className='course-list'>
            {
                courses.map(((course, index) =>
                    <li key={index}>
                        <h3>{course.name}</h3>
                        <p>Start Date: {course.start_date}</p>
                    </li>
                ))
            }
        </ul>
    )
}

export default CourseList