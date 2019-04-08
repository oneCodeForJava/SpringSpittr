package spittr.test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import spittr.dao.SpitterRepository;
import spittr.entity.Spitter;
import spittr.entity.Spittle;
import spittr.web.HomeController;
import spittr.web.SpitterController;
import spittr.web.SpittleController;

public class HomeControllerTest {
	
	@Test
	public void testHomePage() throws Exception{
		HomeController controller = new HomeController();
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
		.andExpect(MockMvcResultMatchers.view().name("home"));
		
	}
	
	@Test
	public void shouldShowRecentSpittles() throws Exception{
		List<Spittle> expectedSpittles = createSpittleList(20);
		SpitterRepository mockRepository = Mockito.mock(SpitterRepository.class);
		Mockito.when(mockRepository.findSpittles(Long.MAX_VALUE, 20)).thenReturn(expectedSpittles);
		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/spittles"))
			.andExpect(MockMvcResultMatchers.view().name("spittles"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("spittleList"))
			.andExpect(MockMvcResultMatchers.model().attribute("spittleList", Matchers.hasItem(expectedSpittles.toArray())));
	}
	
	@Test
	public void shouldShowPagedSpittles() throws Exception{
		List<Spittle> expectedSpittles = createSpittleList(50);
		SpitterRepository mockRepository = Mockito.mock(SpitterRepository.class);
		Mockito.when(mockRepository.findSpittles(238900, 50)).thenReturn(expectedSpittles);
		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/spittles?max=238900&count=50"))
			.andExpect(MockMvcResultMatchers.view().name("spittles"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("spittleList"))
			.andExpect(MockMvcResultMatchers.model().attribute("spittleList", Matchers.hasItem(expectedSpittles.toArray())));
	}
	
	@Test
	public void testSpittle() throws Exception{
		Spittle expectedSpittle = new Spittle("Hello", new Date());
		SpitterRepository mockRepository = Mockito.mock(SpitterRepository.class);
		Mockito.when(mockRepository.findOne(12345)).thenReturn(expectedSpittle);
		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/spittles/12345"))
			.andExpect(MockMvcResultMatchers.view().name("spittle"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("spittle"))
			.andExpect(MockMvcResultMatchers.model().attribute("spittle", expectedSpittle));
	}
	
	@Test
	public void shouldShowRegistration() throws Exception{
		SpitterRepository mockRepository = Mockito.mock(SpitterRepository.class);
		SpitterController controller = new SpitterController(mockRepository);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/spitter/register"))
		.andExpect(MockMvcResultMatchers.view().name("registerForm"));
		
	}
	
	@Test
	public void shouldProcessRegistration() throws Exception{
		SpitterRepository mockRepository = Mockito.mock(SpitterRepository.class);
		Spitter unsaved = new Spitter("jbauer", "24hours", "Jack", "Bauer");
		Spitter saved = new Spitter(24L, "jbauer", "24hours", "Jack", "Bauer");
		Mockito.when(mockRepository.save(unsaved)).thenReturn(saved);
		SpitterController controller = new SpitterController(mockRepository);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform(MockMvcRequestBuilders.post("/spitter/register")
				.param("firstName", "Jack")
				.param("lastName", "Bauer")
				.param("username", "jbauer")
				.param("password", "24hours"))
			.andExpect(MockMvcResultMatchers.redirectedUrl("/spitter/jbauer"));
		Mockito.verify(mockRepository, Mockito.atLeastOnce()).save(unsaved);
			
	}
	
	private List<Spittle> createSpittleList(int count){
		List<Spittle> spittles = new ArrayList<Spittle>();
		for(int i = 0; i < count; i++){
			spittles.add(new Spittle("Spittle" + i, new Date()));
		}
		return spittles;
	}
}
