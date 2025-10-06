import { Component } from '@angular/core';
import {Header} from '../../../components/logged/header/header';
import {Card} from '../../../components/logged/card/card';
import {RouterLink} from '@angular/router';
import {Movie} from '../../../interfaces/movie.interface';

@Component({
  selector: 'app-login',
  imports: [
    Header,
    Card,
    RouterLink,
  ],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {
  movies: Movie[] = [
    {
      id:1,
      title: "O Poderoso Chefão",
      description:"Dom Corleone, chefe da máfia, precisa passar o legado para seu filho Michael, que reluta em assumir os negócios da família." ,
      duration: "1:30h",
      ageRating: "Somente +18",
      approvalRating: 67,
      streamingLogo: "images/streaming/apple.png",
      isTop10: true
    },
    {
      id:2,
      title: "O Senhor dos Anéis: A Sociedade do Anel",
      description: "Um hobbit recebe a missão de destruir um anel poderoso e salva a Terra Média das trevas.",
      duration: "2:58h",
      ageRating: "Livre para todos os públicos",
      approvalRating: 99,
      streamingLogo: "images/streaming/prime-video.png",
      isTop10: true
    },
    {
      id:3,
      title: "Matrix",
      description: "Um hacker descobre que a realidade é uma simulação e lidera uma rebelião contra máquinas inteligentes.",
      duration: "2:16h",
      ageRating: "Somente +16",
      approvalRating: 88,
      streamingLogo: "images/streaming/netflix.png",
      isTop10: true
    },
    {
      id: 4,
      title: "Clube da Luta",
      description: "Um homem insone forma um clube de combate underground que se transforma em um movimento anti-social.",
      duration: "2:19h",
      ageRating: "Somente +18",
      approvalRating: 79,
      streamingLogo: "images/streaming/prime-video.png",
      isTop10: true
    },
    {
      id: 5,
      title: "Pulp Fiction: Tempo de Violência",
      description: "Histórias de criminosos, boxeadores e gângsters se entrelaçam em Los Angeles de forma violenta e não linear.",
      duration: "2:34h",
      ageRating: "Somente +18",
      approvalRating: 92,
      streamingLogo: "images/streaming/apple.png",
      isTop10: false
    },
    {
      id: 6,
      title: "Forrest Gump: O Contador de Histórias",
      description: "Um homem simples testemunha e influencia eventos históricos nos Estados Unidos pós-guerra.",
      duration: "2:22h",
      ageRating: "Livre para todos os públicos",
      approvalRating: 85,
      streamingLogo: "images/streaming/apple.png",
      isTop10: false
    },
    {
      id: 7,
      title: "Warcraft: O Primeiro Encontro de Dois Mundos",
      description: "O mundo fantástico de Azeroth se encontra à beira da guerra quando uma raça de guerreiros orcs invade o planeta através de um portal.",
      duration: "2:03h",
      ageRating: "Somente +12",
      approvalRating: 29,
      streamingLogo: "images/streaming/prime-video.png",
      isTop10: false
    },
    {
      id: 8,
      title: "Um Amor de Tesouro",
      description: "Um caçador de tesouros obcecado arrisca seu casamento para encontrar um lendário navio espanhol carregado de ouro.",
      duration: "2:11h",
      ageRating: "Livre para todos os públicos",
      approvalRating: 72,
      streamingLogo: "images/streaming/netflix.png",
      isTop10: false
    }
  ]

}
