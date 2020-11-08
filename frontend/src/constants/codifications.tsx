import { Icon } from "../components";
import { CodificationMethod } from "../enums/CodificationMethod";
import React from 'react';
import { Codification } from '../models/codification'

export default [
    new Codification(
        "Goulomb",
        "goulomb",
        CodificationMethod.GOULOMB,
        <Icon.Goulomb size={40} color="#333" />
    ),
    new Codification(
        "Elias Gamma",
        "elias-gamma",
        CodificationMethod.ELIAS_GAMMA,
        <Icon.EliasGamma size={40} color="#333" />
    ), new Codification(
        "Delta",
        "delta",
        CodificationMethod.DELTA,
        <Icon.Delta size={40} color="#333" />
    ), new Codification(
        "Fibonacci",
        "fibonacci",
        CodificationMethod.FIBONACCI,
        <Icon.Fibonatti size={40} color="#333" />
    ), new Codification(
        "Unario",
        "unary",
        CodificationMethod.UNARIO,
        <Icon.Unario size={40} color="#333" />
    ), new Codification(
        "Hamming",
        "hamming",
        CodificationMethod.HAMMING,
        <Icon.Hamming size={40} color="#333" />
    ), new Codification(
        "Huffman Estático",
        "huffman",
        CodificationMethod.HUFFMAN,
        <Icon.Huffman size={40} color="#333" />
    )
]