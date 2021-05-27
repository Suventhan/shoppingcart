import http from "../http-common";

class ProductService {
    getAllPriceList() {
        return http.get("/itemPrices");
    }

    getAllProducts() {
        return http.get("/products");
    }

    calculatePrice(data) {
        return http.post("/calculatePrice", data);
    }
}

export default new ProductService();