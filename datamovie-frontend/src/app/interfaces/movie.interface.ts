export interface Category {
  id: number;
  name: string;
}

export interface Streaming {
  id: number;
  name: string;
}

export interface Movie {
  id: number;
  title: string;
  description: string;
  releaseDate: string;
  rating: number;
  categories: Category[];
  streamings: Streaming[];
}
