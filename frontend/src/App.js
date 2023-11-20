import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Signup from "./Component/Auth/Signup";
import HomePage from "./Pages/HomePage";
import Login from "./Component/Auth/Login";
import ProfilePage from "./Pages/ProfilePage";
import Logout from "./Component/Auth/Logout";
import ProblemPage from "./Pages/ProblemPage";

function App() {
  return (
    <div
      style={{
        backgroundColor: "black",
      }}
    >
      <Router>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/profile" element={<ProfilePage />} />
          <Route path="/logout" element={<Logout />} />
          <Route path="/problem/twoSum" element={<ProblemPage />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
