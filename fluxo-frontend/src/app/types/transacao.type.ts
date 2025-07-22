export type Transacao = {
    id: number,
    idUsuario: number,
    valor: number,
    descricao: string,
    dataTransacao: Date,
    dataCriacao: Date,
    tipoTransacao: string,
    mesesRecorrencia: number,
    idCategoria: number
}