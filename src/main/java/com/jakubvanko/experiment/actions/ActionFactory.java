package com.jakubvanko.experiment.actions;

import com.jakubvanko.experiment.OutputStrategy;

public enum ActionFactory {
    REMOVE_EMPTY_EMAIL {
        @Override
        public Action createAction(OutputStrategy outputStrategy) {
            return new RemoveEmptyAction(2);
        }
    },
    REMOVE_EMPTY_ADDRESS {
        @Override
        public Action createAction(OutputStrategy outputStrategy) {
            return new RemoveEmptyAction(3);
        }
    },
    TOTAL_PRICE_YEAR {
        @Override
        public Action createAction(OutputStrategy outputStrategy) {
            return new TotalPriceYearAction(outputStrategy);
        }
    },
    AVERAGE_PRICE_YEAR {
        @Override
        public Action createAction(OutputStrategy outputStrategy) {
            return new AveragePriceYearAction(outputStrategy);
        }
    },
    TOP_THREE_CUSTOMERS {
        @Override
        public Action createAction(OutputStrategy outputStrategy) {
            return new TopThreeCustomersAction(outputStrategy);
        }
    };

    public abstract Action createAction(OutputStrategy outputStrategy);
}
