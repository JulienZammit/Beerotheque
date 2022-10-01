import axios from 'axios';
import BeerApi from "@/api/BeerApi";
import {Beer, PageBeer, SuccessResponse} from "@/interfaces/Beer";

export function jwtInterceptor() {
    axios.interceptors.response.use(success => {
        const url = (success.request as XMLHttpRequest).responseURL;
        if (url.startsWith(BeerApi.beerApiUrl)) {
            let result: SuccessResponse;
            if (url.replace(BeerApi.beerApiUrl, '').split('?')[0].length == 0) {
                result = {data: (success.data as PageBeer).content}
            } else {
                result = {data: success.data as Beer}
            }
            console.log(result)
            return result;
        }
        return success;
    });
}