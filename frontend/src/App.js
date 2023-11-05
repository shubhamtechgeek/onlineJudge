import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Signup from "./Component/Auth/Signup";
import HomePage from "./Pages/HomePage";
import Login from "./Component/Auth/Login";

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
        </Routes>
      </Router>
    </div>
  );
}

export default App;
