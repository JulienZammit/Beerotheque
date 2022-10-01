import * as constants from "@/utils/constants";

class utils {
    priceExcludingTaxes(beerPrice: number): number {
        return Math.round(beerPrice + (beerPrice * constants.TVA)) / 100;
    }
}

export default new utils()