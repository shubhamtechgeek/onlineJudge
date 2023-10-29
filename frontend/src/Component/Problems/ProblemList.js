import React, { useState } from "react";

const ProblemList = () => {

    const employees = 
        [
          { name: 'John Doe', dob: '24/05/1995', position: 'Web Developer', salary: '$120,000' },
          { name: 'Jane Doe', dob: '04/11/1980', position: 'Web Designer', salary: '$100,000' },
          { name: 'Gary Barlow', dob: '24/05/1995', position: 'Singer', salary: '$20,000' }
        ];


  return (
    <div>
        <h5 className="text-white mt-8 text-center font-mono">Problem List</h5>
      <div className="overflow-auto">
        <table className="mx-auto divide-y-4 divide-green-600 bg-white text-sm dark:divide-green-700 dark:bg-black">
          <thead className="ltr:text-left rtl:text-right">
            <tr>
              <th className="whitespace-nowrap px-16 py-8 font-medium text-gray-900 dark:text-white">
                Name
              </th>
              <th className="whitespace-nowrap px-16 py-8 font-medium text-gray-900 dark:text-white">
                Date of Birth
              </th>
              <th className="whitespace-nowrap px-16 py-8 font-medium text-gray-900 dark:text-white">
                Role
              </th>
              <th className="whitespace-nowrap px-16 py-8 font-medium text-gray-900 dark:text-white">
                Salary
              </th>
            </tr>
          </thead>

          <tbody className="divide-y divide-gray-200 dark:divide-green-600">
      {employees.map((employee, index) => (
        <tr key={index}>
          <td className="whitespace-nowrap px-16 py-2 font-medium text-gray-900 dark:text-white">
            {employee.name}
          </td>
          <td className="whitespace-nowrap px-16 py-2 text-gray-700 dark:text-gray-200">
            {employee.dob}
          </td>
          <td className="whitespace-nowrap px-16 py-2 text-gray-700 dark:text-gray-200">
            {employee.position}
          </td>
          <td className="whitespace-nowrap px-16 py-2 text-gray-700 dark:text-gray-200">
            {employee.salary}
          </td>
        </tr>
      ))}
    </tbody>
        </table>
      </div>
    </div>
  );
};

export default ProblemList;
