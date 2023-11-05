import React from "react";
import { useNavigate } from "react-router-dom";

const Logout = () => {
    const navigate = useNavigate();

    React.useEffect(() => {
        const handleLogout = () => {
            localStorage.removeItem('user');
            localStorage.removeItem('token');
            navigate('/login');
        };

        handleLogout();
    }, [navigate]); // Include 'navigate' in the dependency array

    return null; // You can return null since this component doesn't render anything.
}

export default Logout;