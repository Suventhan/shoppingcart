import React, { Component } from 'react';
import { Table } from 'react-bootstrap';
import { connect } from 'react-redux';
import { getPriceList } from "../actions/products";

class ProductPriceList extends Component {

    componentDidMount() {
        this.props.getPriceList();
    }

    render() {
        const { priceList } = this.props;

        const columns = ["#", "Name", "Units", "Price"];

        return (<div>
            <Table hover>
            <thead>
                <tr>
                  {columns.map(val => (<th key={val} scope="col">{val}</th>))}
                </tr>
            </thead>
                <tbody>
                {priceList && priceList.map((product, index) => {
                    return (
                        <tr key={index}>
                            <td>{index+1}</td>
                            <td>{product.name}</td>
                            <td>{product.units}</td>
                            <td>{product.price}</td>
                        </tr>
                    )
                })}
                </tbody>
            </Table>
        </div>);
    }
}

const mapStateToProps = (state) => {
    return {
        priceList: state.products.priceList,
    };
};

export default connect(mapStateToProps, { getPriceList })(ProductPriceList);