import { ReactElement } from "react";

export class Codification {
  name: string;
  urlName: string;
  codificationType: number;
  icon: ReactElement;
  explanationImage: any;

  constructor(
    name: string,
    urlName: string,
    codificationType: number,
    icon: ReactElement,
    explanationImage: any
  ) {
    this.name = name;
    this.urlName = urlName;
    this.codificationType = codificationType;
    this.icon = icon;
    this.explanationImage = explanationImage;
  }
}
