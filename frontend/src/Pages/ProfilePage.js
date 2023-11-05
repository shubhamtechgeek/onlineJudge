import React from "react";
import { useEffect} from "react";
import { useNavigate } from "react-router-dom";

import Navbar from "../Component/Navigation/Navbar";
import Profile from "../Component/Profile/Profile";

const ProfilePage = () => {
  const navigate = useNavigate();
  useEffect(() => {
    const verifyCookie = async () => {
      if (!localStorage.getItem('token')) {
        navigate("/login");
      }
    }
    verifyCookie();
  }, [navigate]);

  return (
    <>
      <div>
        <Navbar />
      </div>
      <div>
        <Profile />
      </div>
    </>
  );
};

export default ProfilePage;
