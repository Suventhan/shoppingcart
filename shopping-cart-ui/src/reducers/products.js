import {
    GET_PRICE_LIST,
    GET_PRODUCTS,
    CALCULATE_PRICE
} from "../actions/types";

const initialState = [];

function productsReducer( products = initialState, action) {
    const { type, payload } = action;

    switch (type) {
        case GET_PRICE_LIST:
            console.log("Reducer Price: ", products);
            return { priceList: payload };

        case GET_PRODUCTS:
            return { products: payload };

        case CALCULATE_PRICE:
            return { ...products, shoppingCart: payload };

        default:
            return products;
    }
}

export default productsReducer;