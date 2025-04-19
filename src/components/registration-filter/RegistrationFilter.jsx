import React from 'react'
import './RegistrationFilter.css'

const RegistrationFilter = ({ selectedRegistrationsNumber, onSelectRegistrationsNumber }) => {
    return (
        <div className='registration-filter'>
            <h2>List of courses with number of registrations:</h2>
            <select value={selectedRegistrationsNumber} onChange={(e) => onSelectRegistrationsNumber(e.target.value)}>
                <option value="" disabled={selectedRegistrationsNumber != ""}>Select a number</option>
                <option value="0">No registrations</option>
                <option value="1 to 10">1 - 10 registrations</option>
                <option value="11 to 30">11 - 30 registrations</option>
                <option value="over 30">Over 30 registrations</option>
            </select>
        </div>
    )
}

export default RegistrationFilter