import axios from "axios";

const BEER_API = "http://localhost:8383/api/beers";

class BeerService {

    getAllBeers(nbrPage, nameSort, typeSort) {
        let params = new URLSearchParams();
        let request = {
            params: params
        };
        if (nameSort == undefined || typeSort == undefined) {
            if (nbrPage == undefined) {
                params.append("pageNumber", 0);
            } else {
                params.append("pageNumber", nbrPage);
            }
        } else {
            params.append("pageNumber", nbrPage);
            params.append("nameSort", nameSort);
            params.append("typeSort", typeSort);
        }
        return axios.get(BEER_API, request)
    }

    deleteBeer(id) {
        return axios.delete(`${BEER_API + "/" + id}`)
    }

    saveBeer(beer) {
        let saveFunction = beer.id ? axios.put : axios.post;
        return saveFunction(`${BEER_API}${beer.id ? "/" + beer.id : ""}`, beer);
    }
}

export default new BeerService()