import {
    GET_PRICE_LIST,
    GET_PRODUCTS,
    CALCULATE_PRICE
} from "./types";

import ProductService from "../services/product.service";

export const getPriceList = () => async (dispatch) => {
    try {
        const res = await ProductService.getAllPriceList();

        dispatch({
            type: GET_PRICE_LIST,
            payload: res.data,
        });
    } catch (err) {
        console.log(err);
    }
}

export const getProducts = () => async (dispatch) => {
    try {
        const res = await ProductService.getAllProducts();

        dispatch({
            type: GET_PRODUCTS,
            payload: res.data,
        });
    } catch (err) {
        console.log(err);
    }
}

export const calculatePrice = (data) => async (dispatch) => {
    try {
        const res = await ProductService.calculatePrice(data);

        dispatch({
            type: CALCULATE_PRICE,
            payload: res.data,
        });
    } catch (err) {
        console.log(err);
    }
}