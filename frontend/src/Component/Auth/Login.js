import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";

const Login = () => {
  const navigate = useNavigate();
  const [inputValue, setInputValue] = useState({
    email: "",
    password: "",
  });
  const { email, password } = inputValue;
  const handleOnChange = (e) => {
    const { name, value } = e.target;
    setInputValue({
      ...inputValue,
      [name]: value,
    });
  };

  const handleError = (err) =>
    toast.error(err, {
      position: "bottom-left",
    });
  const handleSuccess = (msg) =>
    toast.success(msg, {
      position: "bottom-left",
    });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const {data}  = await axios.post(
        "http://localhost:5000/login",
        {
          ...inputValue,
        },
        { withCredentials: true }
      );
      
      console.log(data);
      localStorage.setItem("token",data.token);
      localStorage.setItem("user", data.email)
   
      const { success, message } = data;
      if (success) {
        handleSuccess(message);
          navigate("/");
      } else {
        handleError(message);
      }
    } catch (error) {
      console.log(error);
    }
    setInputValue({
      ...inputValue,
      email: "",
      password: "",
    });
  };

  return (
    <div className="w-full max-w-xs">
      <h5 className="font-mono font-bold text-center text-white" >Login Account</h5>
      <form className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4" onSubmit={handleSubmit}>
        <div className="mb-4">
          <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="email">Email </label>
          <input className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            type="email"
            name="email"
            value={email}
            placeholder="Enter your email"
            onChange={handleOnChange}
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="password">Password </label>
          <input className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            type="password"
            name="password"
            value={password}
            placeholder="Enter your password"
            onChange={handleOnChange}
          />
        </div>
        <button className="inline-flex items-center justify-center gap-1.5 rounded-lg border border-green-600 bg-green-600 px-5 py-1 text-sm font-medium text-white transition hover:bg-black focus:outline-none focus:ring" type="submit">Submit</button>
        <div>
        <span>Already have an account? <Link to={"/signup"}>Signup</Link></span>
        </div>
        
      </form>
      <ToastContainer />
    </div>
  );
};

export default Login;
