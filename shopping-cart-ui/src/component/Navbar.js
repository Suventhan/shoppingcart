import React from 'react';
import { Link } from 'react-router-dom';
import "bootstrap/dist/css/bootstrap.min.css";

const Navbar = () => (
    <nav className="navbar navbar-expand navbar-dark bg-dark" style={{ padding : '15px'}}>
        <Link to={"/"} className="navbar-brand">
            Product Store
         </Link>
        <div className="navbar-nav mr-auto" style={{ padding : '10px'}}>
            <li className="nav-item">
                <Link to="/" className="nav-link">Price List</Link>
            </li>
            <li className="nav-item">
                <Link to="/cart" className="nav-link">Shopping Cart</Link>
            </li>
        </div>
    </nav>
)

export default Navbar;