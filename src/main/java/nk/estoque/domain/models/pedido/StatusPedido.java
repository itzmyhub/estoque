package nk.estoque.domain.models.pedido;

public enum StatusPedido {
    CRIADO {
        @Override
        public boolean podeTransitarPara(StatusPedido destino) {
            return destino == CONFIRMADO || destino == CANCELADO;
        }
    },
    CONFIRMADO {
        @Override
        public boolean podeTransitarPara(StatusPedido destino) {
            return destino == EM_ANDAMENTO || destino == CANCELADO;
        }
    },
    EM_ANDAMENTO {
        @Override
        public boolean podeTransitarPara(StatusPedido destino) {
            return destino == CONCLUIDO || destino == CANCELADO;
        }
    },
    CONCLUIDO {
        @Override
        public boolean podeTransitarPara(StatusPedido destino) {
            return destino == FATURADO;
        }
    },
    FATURADO {
        @Override
        public boolean podeTransitarPara(StatusPedido destino) {
            return destino == PAGO;
        }
    },
    PAGO, CANCELADO;

    public boolean podeTransitarPara(StatusPedido destino) {
        return false;
    }
}
