import React from 'react'
import './CourseChart.css'

//change
const CourseChart = ({ courses, 
  // students
  registrantsCountPerCourse }) => {
  // const courseCounts = courses.map(course =>
  //   students.filter(student => student.courseId == course.id).length
  // );

  return (
    <div className='chart-container'>
      <div className='chart'>
        {
          registrantsCountPerCourse.map((courseCount, index) =>
            <div key={index} className='chart-bar-inner'
              style={{ height: `${courseCount / registrantsCountPerCourse.reduce((sum,count) => sum + count, 0) * 100}%` }}>
              {courseCount}
            </div>)
        }
      </div>
      <div className='names'>
        {
          courses.map((course, index) =>
            <div className='chart-bar-label' key={index}>{course.name}</div>)
        }
      </div>
    </div>
  )
}

export default CourseChart