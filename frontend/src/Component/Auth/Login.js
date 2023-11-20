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
      const { data } = await axios.post(
        "http://localhost:5000/login",
        {
          ...inputValue,
        },
        { withCredentials: true }
      );

      console.log(data);
      localStorage.setItem("token", data.token);
      localStorage.setItem("user", data.email);

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
    <div className="h-screen w-screen flex items-center justify-center">
      <div>
        <div className="text-center">
          <h1 className="text-2xl font-bold text-white sm:text-3xl font-mono">
            <Link to="/">AlgoJudge</Link>
          </h1>

          <p className="mt-1.5 text-sm font-mono text-green-600">
            This is an online judge which looks good :{")"}
          </p>
        </div>
        <h1 className="font-mono text-center text-white mt-8 mb-8">
          Login Account
        </h1>
        <form
          className="bg-black overflow-auto flex flex-col justify-center items-center"
          onSubmit={handleSubmit}
        >
          <div className="mb-4">
            <label
              className="ml-6 text-green-600 text-sm font-mono mb-2"
              htmlFor="email"
            >
              Email{" "}
            </label>
            <input
              className="shadow appearance-none font-mono border-green-600 bg-black border-2 rounded py-2 px-3 text-white leading-tight focus:outline-none focus:shadow-outline"
              type="email"
              name="email"
              value={email}
              placeholder="Enter your email"
              onChange={handleOnChange}
            />
          </div>
          <div className="mb-4">
            <label
              className="text-green-600 text-sm font-mono mb-2"
              htmlFor="password"
            >
              Password{" "}
            </label>
            <input
              className="shadow appearance-none font-mono border-green-600 bg-black border-2 rounded py-2 px-3 text-white leading-tight focus:outline-none focus:shadow-outline"
              type="password"
              name="password"
              value={password}
              placeholder="Enter your password"
              onChange={handleOnChange}
            />
          </div>
          <button
            className="inline-flex font-mono items-center justify-center gap-1.5 rounded-lg border border-green-600 bg-green-600 px-5 py-1 text-sm font-medium text-white transition hover:bg-black focus:outline-none focus:ring"
            type="submit"
          >
            Submit
          </button>
          <div className="mt-8">
            <span className="text-white font-mono">
              Register Here {"=>"}  <Link className="underline text-green-600" to={"/signup"}>Signup</Link>
            </span>
          </div>
        </form>
        <ToastContainer />
      </div>
    </div>
  );
};

export default Login;
