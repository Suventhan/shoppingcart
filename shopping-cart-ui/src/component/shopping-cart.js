import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Button, Dropdown, DropdownButton, Table } from 'react-bootstrap';
import { getProducts, calculatePrice } from '../actions/products';

class ShoppingCart extends Component {

    constructor(props) {
        super(props);
        this.handleSelect = this.handleSelect.bind(this);
        this.handleInputChange = this.handleInputChange.bind(this);
        this.submitCalculations = this.submitCalculations.bind(this);

        this.state = {
            type : ['Carton', 'Carton'],
            quantity: [0, 0]
        }
    }

    componentDidMount() {
        this.props.getProducts();
    }

    handleSelect(e, id) {
        console.log(e);
        const { type } = this.state;
        type[id-1] = e;
        this.setState({ type });
    }

    handleInputChange(e, id) {

        const { quantity } = this.state;

        quantity[id-1] = e.target.value;
        
        this.setState({ quantity: quantity });
    }

    submitCalculations() {
        const { products, calculatePrice } = this.props;
        const { type, quantity } = this.state;

        const selectedItems =[];

        if (quantity && quantity.length > 0) {
            quantity.forEach((val, index) => {
                if (val && val > 0) {
                    const product = products.find(p => p.id === (index + 1));
                    const item = {
                        id : product.id,
                        name: product.name,
                        units: val,
                        unitType: type[product.id - 1].toUpperCase()
                    };
        
                    selectedItems.push(item);
                }
            })
    
            calculatePrice({ selectedItems });
        }
    }

    render() {
        const { products, shoppingCart } = this.props;
        const { type, quantity } = this.state;

        const columns = ["#", "Name", "Type", "Units", "Price"];

        return (<div>
            <Table hover>
                <thead>
                    <tr>
                        {columns.map(val => (<th key={val} scope="col">{val}</th>))}
                    </tr>
                </thead>
                <tbody>
                    {products && products.map((product, index) => {
                        return (
                            <tr key={index}>
                                <td>{product.id}</td>
                                <td>{product.name}</td>
                                <td>{<DropdownButton
                                        key="drop-quantity"
                                        id={`dropdown-variants-secondary`}
                                        title={type[product.id - 1]}
                                        variant='light'
                                        onSelect={e => this.handleSelect(e, product.id)}
                                    >
                                        <Dropdown.Item eventKey="Carton">Carton</Dropdown.Item>
                                        <Dropdown.Item eventKey="Unit">Unit</Dropdown.Item>
                                    </DropdownButton>}
                                </td>
                                <td>
                                    <input 
                                        key="input" 
                                        type="number" 
                                        onChange={e => this.handleInputChange(e, product.id)} 
                                        value={quantity[product.id-1]}
                                        style={{ width: '60px' }}
                                    />
                                </td>
                                <td>{(shoppingCart && shoppingCart.selectedItems && 
                                    shoppingCart.selectedItems.length > 0 && shoppingCart.selectedItems.find(item => item.id === product.id)) ? 
                                    shoppingCart.selectedItems.find(item => item.id === product.id).price : 0}
                                </td>
                            </tr>
                        )
                    })}
                </tbody>
            </Table>
            <div style={{ float: 'right' }}>
                <span>{`Total Price: ${(shoppingCart) ? shoppingCart.price : 0}`}</span>
                <div>
                    <Button variant="secondary" size="lg" onClick={this.submitCalculations}>
                        Calculate Price
                    </Button>
                </div>    
            </div>
        </div>);
    }
}

const mapStateToProps = (state) => {
    console.log("State for shopping cart: ", state);
    return {
        products: state.products.products,
        shoppingCart: state.products.shoppingCart
    }
}

export default connect(mapStateToProps, { getProducts, calculatePrice })(ShoppingCart);