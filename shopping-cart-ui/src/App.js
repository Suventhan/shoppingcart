import React, { Component } from 'react';
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import './App.css';

import NavBar from "./component/Navbar";
import ProductPriceList from "./component/products-price-list";
import ShoppingCart from "./component/shopping-cart";

class App extends Component {
  render() {
    return (
      <div className="App">
        <Router>
          <NavBar />
  
          <div className="container mt-3">
            <Switch>
              <Route exact path="/" component={ProductPriceList} />
              <Route exact path="/cart" component={ShoppingCart} />
            </Switch>
          </div>
        </Router>
      </div>
    );
  }
}

export default App;
