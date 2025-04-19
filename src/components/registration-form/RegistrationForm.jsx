import React, { useState } from 'react'
import './RegistrationForm.css'


const RegistrationForm = ({ courses, onRegister }) => {
    const [studentName, setStudentName] = useState("");
    const [studentAge, setStudentAge] = useState("");
    const [studentEmail, setStudentEmail] = useState("");
    const [studentPhone, setStudentPhone] = useState("");
    const [courseId, setCourseId] = useState("");
    const [courseStartDate, setCourseStartDate] = useState("");

    const handleChangeName = (e) => {
        setStudentName(e.target.value);
    }
    const handleChangeAge = (e) => {
        setStudentAge(e.target.value);
    }
    const handleChangeEmail = (e) => {
        setStudentEmail(e.target.value);
    }
    const handleChangePhone = (e) => {
        setStudentPhone(e.target.value);
    }
    const handleChangeCourse = (e) => {
        setCourseId(e.target.value);
        setCourseStartDate(courses.find(course => course.id == e.target.value).start_date);
    }
    const handleReset = () => {
        setStudentName("");
        setStudentAge("");
        setStudentEmail("");
        setStudentPhone("");
        setCourseId("");
        setCourseStartDate("");
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        if(!studentName || !studentAge || !studentEmail || !studentPhone || !courseId || !courseStartDate) {
            return;
        }
        const newStudent = {
            name: studentName,
            age: studentAge,
            email: studentEmail,
            phone: studentPhone,
          // change
            // courseId: courseId,
            // courseStartDate: courseStartDate
        }
        onRegister(newStudent,parseInt(courseId));
        handleReset();
    }

    return (
        <form className="registration-form" onSubmit={handleSubmit}>
            <div className="input-container">
                <label htmlFor="name">Name:</label>
                <input id='name' type="text" value={studentName} onChange={handleChangeName} required />
            </div>
            <div className="input-container">
                <label htmlFor="age">Age:</label>
                <input id='age' type="number" min={0} max={120} value={studentAge} onChange={handleChangeAge} required />
            </div>
            <div className="input-container">
                <label htmlFor="email">Email:</label>
                <input id='email' type="email" value={studentEmail} onChange={handleChangeEmail} placeholder='israel@example.com' required />
            </div>
            <div className="input-container">
                <label htmlFor="phone">Phone:</label>
                <input id='phone' type="tel" value={studentPhone} onChange={handleChangePhone} placeholder='+972542663377' required />
            </div>
            <div className='input-container'>
                <label htmlFor="course">Course:</label>
                <select id="course" value={courseId} onChange={handleChangeCourse} required>
                    <option value="" disabled>Select Course</option>
                    {
                        courses.map((course) =>
                            <option key={course.id} value={course.id}>{course.name}</option>
                        )
                    }
                </select>
            </div>
            <div className="input-container">
                <label htmlFor="date">Start Date:</label>
                <input id='date' type="date" value={courseStartDate} readOnly />
            </div>
            <div className='buttons-container'>
                <button type="button" onClick={handleReset}>Reset</button>
                <button type='submit'>Register</button>
            </div>
        </form>
    )
}

export default RegistrationForm