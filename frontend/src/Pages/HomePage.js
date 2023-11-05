import React from "react";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useCookies } from "react-cookie";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
import Navbar from "../Component/Navigation/Navbar";
import ProblemList from "../Component/Problems/ProblemList";

const HomePage = () => {
  const navigate = useNavigate();
  const [cookies, removeCookie] = useCookies([]);
  const [username, setUsername] = useState("");
  useEffect(() => {
    const verifyCookie = async () => {
      console.log(cookies.token);
      if (!localStorage.getItem('token')) {
        navigate("/login");
      }
    }
    verifyCookie();
  }, [cookies, navigate]);

  return (
    <>
      <div>
        <Navbar />
      </div>
      <div>
        <ProblemList />
      </div>
      <ToastContainer />
    </>
  );
};

export default HomePage;
