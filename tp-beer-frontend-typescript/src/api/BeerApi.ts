import axios from "axios";
import {Beer, SuccessResponse} from "@/interfaces/Beer";


class BeerApi {
    beerApiUrl = `${process.env.VUE_APP_BEER_API}/beers/`;

    getAllBeer(nbrPage: number, request: { params: URLSearchParams }): Promise<SuccessResponse> {
        return axios.get(this.beerApiUrl, request);
    }

    deleteBeer(id: string): Promise<void> {
        return axios.delete(`${this.beerApiUrl + id}`)
    }

    saveBeer(beer: Beer): Promise<void> {
        const saveFunction = beer.id ? axios.put : axios.post;
        return saveFunction(`${this.beerApiUrl}${beer.id ? beer.id : ""}`, beer);
    }
}

export default new BeerApi()