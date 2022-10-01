import {KIND_OF_BEER} from "@/enums/enumBeers";

export interface Beer {
    id?: number;
    name: string;
    priceExcludingTaxCents: number;
    remark: string;
    alcoholDegrees: number;
    kindOfBeer: KIND_OF_BEER;
    owner: string;
}

export interface PageBeer {
    content: Beer[];
}

export interface SuccessResponse {
    data: Beer | Beer[];
    errors?: string[];
}