import {Beer} from "@/interfaces/Beer";
import BeerApi from "@/api/BeerApi";

class BeerService {

    getAllBeer(nbrPage: number, nameSort?: string, typeSort?: string): Promise<Beer[]> {
        const params = new URLSearchParams();
        const request = {
            params: params
        };
        if (nameSort == undefined || typeSort == undefined) {
            if (nbrPage == undefined) {
                params.append("pageNumber", "0");
            } else {
                params.append("pageNumber", String(nbrPage));
            }
        } else {
            params.append("pageNumber", String(nbrPage));
            params.append("nameSort", nameSort);
            params.append("typeSort", typeSort);
        }
        return BeerApi.getAllBeer(nbrPage, request).then(response => {
            return (response.data as Beer[])
        });
    }

    deleteBeer(id: string): Promise<void> {
        return BeerApi.deleteBeer(id).then();
    }

    saveBeer(beer: Beer): Promise<void> {
        return BeerApi.saveBeer(beer).then();
    }
}

export default new BeerService()