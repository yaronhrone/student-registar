import React from 'react'
import './CourseFilter.css'

const CourseFilter = ({ courses, selectedCourse, onSelectCourse }) => {
  
  return (
    <div className='course-filter'>
      <h2>List of students registered for:</h2>
      <select value={selectedCourse} onChange={(e) => onSelectCourse(e.target.value)}>
        <option value="">All Courses</option>
        {
          courses.map((course, index) =>
            <option key={index} value={course.id}>{course.name}</option>)
        }
      </select>
    </div>
  )
}

export default CourseFilter