import { useState } from "react";
import avatar from "./assets/avatar";
import PokemonForm from "./components/PokemonForm";
import PokemonCard from "./components/PokemonCard";

function App() {
  const [pokemon, setPokemon] = useState([
    {
      id: 1,
      name: "Litten",
      type: "Fire",
      image: avatar.litten
    },
    {
      id: 2,
      name: "Glaceon",
      type: "Ice",
      image: avatar.glaceon
    },
    {
      id: 3,
      name: "Evee",
      type: "Normal",
      image: avatar.evee
    }
  ]);

  function addPokemon(newPokemon) {
    setPokemon([...pokemon, newPokemon]);
  }

  return (
    <div className="min-h-screen bg-linear-to-t from-red-600 to-slate-800 p-6">
      <h1 className="mb-6 text-center text-4xl font-bold text-white">
        Scuffy Pokédex
      </h1>

      <PokemonForm addPokemon={addPokemon} />

      <div className="mx-auto grid max-w-5xl grid-cols-1 gap-5 sm:grid-cols-2 md:grid-cols-3">
        {pokemon.map((p) => (
          <PokemonCard key={p.id} pokemon={p} />
        ))}
      </div>
    </div>
  );
}

export default App;
