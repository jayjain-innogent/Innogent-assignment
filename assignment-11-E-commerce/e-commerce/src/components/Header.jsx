import React from 'react';
import { Link, useLocation } from "react-router-dom";


function Header() {
    // for active link highlight
    const location = useLocation();

    const isActive = (path) => location.pathname === path ? "active-link" : "";

    return (
        <header className="header">
            {/* Left section - Logo */}
            <div className="header-left">
                <Link to="/" className="header-logo">
                    <h1>E-Commerce</h1>
                </Link>
            </div>

            {/* Right section - Navigation icons */}
            <nav className="header-right">
                <Link to="/notifications" className={isActive("/notifications")} >
                    <img
                        className="header-icon"
                        src="/assets/notification.png"
                        alt="Notifications"
                    />
                </Link>

                <Link to="/profile" className={isActive("/profile")}>
                    <img
                        className="header-icon"
                        src="/assets/user.png"
                        alt="User Profile"
                    />
                </Link>

                <Link to="/cart" className={isActive("/cart")}>
                    <img
                        className="header-icon"
                        src="/assets/shoppingBag.png"
                        alt="Shopping Cart"
                    />
                </Link>
            </nav>
        </header >
    );
}

export default Header;
