import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.eclipse.sisu.Parameters;

import java.util.List;

@Mojo(name = "property", defaultPhase = LifecyclePhase.COMPILE)
public class PropertyMojo extends AbstractMojo {
    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    MavenProject project;

    @Parameter(property = "scope")
    String scope;

    @Parameter(property = "additionalPath")
    String additionalPath;

    public void execute() throws MojoExecutionException, MojoFailureException {
        List<Dependency> dependencies;
        dependencies = project.getDependencies();
        String basedir = project.getBasedir().getAbsolutePath();
        getLog().info("Path be like: " + basedir);
        long numDependencies = dependencies.stream()
                .filter(d -> (scope == null || scope.isEmpty()) || scope.equals(d.getScope()))
                .count();
        getLog().info("Number of dependencies: " + numDependencies);
    }
}