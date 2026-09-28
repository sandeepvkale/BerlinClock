import { useState } from "react";
import "./App.css";

const API = "http://localhost:8080/api/clock";
function App() {
  const [time, setTime] = useState("16:50:06");
  const [clock, setClock] = useState("");
  const [berlin, setBerlin] = useState("YRRROROOOYYRYYRYYRYOOOOO");
  const [digital, setDigital] = useState("");
  const [error, setError] = useState("");
  async function toBerlin() {
    setError("");
    try {
      const r = await fetch(
        `${API}/to-berlin?time=${encodeURIComponent(time)}`,
      );
      if (!r.ok) throw new Error(await r.text());
      setClock(await r.text());
    } catch (e) {
      setError(e instanceof Error ? e.message : String(e));
    }
  }
  async function toDigital() {
    setError("");
    try {
      const r = await fetch(
        `${API}/to-digital?berlinTime=${encodeURIComponent(berlin)}`,
      );
      if (!r.ok) throw new Error(await r.text());
      setDigital(await r.text());
    } catch (e) {
      setError(e instanceof Error ? e.message : String(e));
    }
  }

  return (
    <main>
      <h1>Berlin Clock</h1>
      <section>
        <h2>Digital → Berlin</h2>
        <div className="controls">
          <input value={time} onChange={(e) => setTime(e.target.value)} />
          <button onClick={toBerlin}>Convert</button>
        </div>
        <div className="conversion-result">
          {clock && <p><code>{clock}</code></p>}
        </div>
      </section>
      <section>
        <h2>Berlin → Digital</h2>
        <div className="controls">
          <input value={berlin} onChange={(e) => setBerlin(e.target.value)} />
          <button onClick={toDigital}>Convert</button>
        </div>
        <div className="conversion-result">
          {digital && <p><code>{digital}</code></p>}
        </div>
      </section>
      {error && <p className="error">{error}</p>}
    </main>
  );
}

export default App;
