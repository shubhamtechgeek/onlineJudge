import logo from './logo.svg';
import './App.css';
import Navbar from './Component/Navigation/Navbar';
import ProblemList from './Component/Problems/ProblemList';

function App() {
  return (
    <div style={{
      backgroundColor: 'black'}}>
        <div>
        <Navbar/>
        </div>
        <div>
        <ProblemList/>
        </div>
      
      
    </div>
  );
}

export default App;
